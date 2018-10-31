package ru.gavri.accounting.web.hddentity;

import com.haulmont.bpm.gui.procactions.ProcActionsFrame;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.security.entity.User;
import ru.gavri.accounting.entity.HddEntity;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HddEntityEdit extends AbstractEditor<HddEntity> {
    private static final String PROCESS_CODE = "hdd_formatting";

    @Inject
    private ProcActionsFrame frame;

    @Inject
    private UserSessionSource userSessionSource;

    @Override
    public void ready() {
        initProcActionsFrame();
    }

    private void initProcActionsFrame() {
        frame.initializer()
                .standard()
                .setBeforeStartProcessPredicate(() -> {
                    /*User user = userSessionSource.getUserSession().getUser();
                    if (user.getUserRoles().stream().noneMatch(r -> r.getRole().getName().equals("Administrators"))) {
                        showNotification("У вас недостаточно прав для запуска процесса", NotificationType.ERROR);
                        return false;
                    }*/
                    if (getItem().getPk().getPkMovings() != null && getItem().getPk().getPkMovings().size() > 0) {
                        return true;
                    }
                    return false;
                })
                .init(PROCESS_CODE, getItem());
    }
}