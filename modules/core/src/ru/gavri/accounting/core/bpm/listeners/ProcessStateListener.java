package ru.gavri.accounting.core.bpm.listeners;

import com.google.common.base.Strings;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Metadata;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.event.ActivitiActivityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gavri.accounting.entity.HddEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ProcessStateListener implements ActivitiEventListener {

    private static final Logger log = LoggerFactory.getLogger(ProcessStateListener.class);

    private Metadata metadata;

    public ProcessStateListener() {
        metadata = AppBeans.get(Metadata.class);
    }

    @Override
    public void onEvent(ActivitiEvent event) {
        RuntimeService runtimeService = event.getEngineServices().getRuntimeService();
        String executionId = event.getExecutionId();
        UUID entityId = (UUID) runtimeService.getVariable(executionId, "entityId");
        String entityName = (String) runtimeService.getVariable(executionId, "entityName");
        if (entityId == null) {
            log.error("Cannot update process state. entityId variable is null");
            return;
        }
        if (Strings.isNullOrEmpty(entityName)) {
            log.error("Cannot update process state. entityName variable is null");
            return;
        }
        MetaClass metaClass = metadata.getClass(entityName);
        if (metaClass == null) {
            log.error("Cannot update process state. MetaClass {} not found", entityName);
            return;
        }

        switch (event.getType()) {
            case ACTIVITY_COMPLETED:
                String activityName = ((ActivitiActivityEvent) event).getActivityName();
                if (!Strings.isNullOrEmpty(activityName) && activityName.equals("Set true")) {
                    setDateFormatting(metaClass, entityId);
                }
                break;
        }
    }

    private void setDateFormatting (MetaClass metaClass, UUID entityId) {
        Persistence persistence = AppBeans.get(Persistence.class);
        try (Transaction tx = persistence.getTransaction()) {
            EntityManager em = persistence.getEntityManager();
            HddEntity entity = em.createQuery("select h from accounting$HddEntity h where h.uuid = :uuid", HddEntity.class)
                    .setParameter("uuid", entityId)
                    .getSingleResult();
            if (entity != null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date date = new Date();
                entity.setDateOfFormatting(simpleDateFormat.format(date));
            } else {
                log.error("Entity {} with id {} not found", metaClass.getName(), entityId);
            }
            tx.commit();
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
