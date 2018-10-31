package ru.gavri.accounting.core;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import org.springframework.stereotype.Component;
import ru.gavri.accounting.entity.HddEntity;

import javax.inject.Inject;
import java.util.UUID;

@Component("formatting")
public class Formatting {

    @Inject
    private Persistence persistence;

    public void updateState(UUID entityId, Boolean isFormatting) {
        try (Transaction tx = persistence.getTransaction()) {
            HddEntity hddEntity = persistence.getEntityManager().createQuery("select h from accounting$HddEntity h where h.uuid = :uuid", HddEntity.class)
                    .setParameter("uuid", entityId)
                    .getSingleResult();
            if (hddEntity != null) {
                hddEntity.setIsFormatted(isFormatting);
            }
            tx.commit();
        }
    }

}
