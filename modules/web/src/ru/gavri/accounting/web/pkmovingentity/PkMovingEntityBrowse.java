package ru.gavri.accounting.web.pkmovingentity;

import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.data.GroupDatasource;
import ru.gavri.accounting.entity.HddEntity;
import ru.gavri.accounting.entity.PkEntity;
import ru.gavri.accounting.entity.PkMovingEntity;

import javax.inject.Inject;

public class PkMovingEntityBrowse extends AbstractLookup {

    @Inject
    private GroupTable<PkMovingEntity> pkMovingEntitiesTable;
    @Inject
    private GroupDatasource<PkMovingEntity, Long> pkMovingEntitiesDs;

    public void onApproval(Component source) {
        PkMovingEntity singleSelectedItem = pkMovingEntitiesTable.getSingleSelected();
        if (singleSelectedItem != null && !singleSelectedItem.getApproval()) {
            PkEntity pkEntity = singleSelectedItem.getPkEntity();
            boolean allFormattedDisks = pkEntity.getHardDisks().stream().allMatch(HddEntity::getIsFormatted);
            if (allFormattedDisks) {
                pkMovingEntitiesDs.getItem(singleSelectedItem.getId()).setApproval(true);
                pkMovingEntitiesDs.commit();
                showNotification("Перемещение подтверждено", NotificationType.HUMANIZED);
            } else {
                showNotification("Подтвердить данное перемещение невозможно, т.к не все носители были отформатированы", NotificationType.ERROR);
            }
        } else {
            showNotification("Не выбрано перемещение или выбранное пермещение уже подтверждено", NotificationType.ERROR);
        }
    }
}