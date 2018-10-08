package ru.gavri.accounting.web.pkmovingentity;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import ru.gavri.accounting.entity.HddEntity;
import ru.gavri.accounting.entity.PkEntity;
import ru.gavri.accounting.entity.PkMovingEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

public class PkMovingEntityEdit extends AbstractEditor<PkMovingEntity> {
    @Inject
    private Datasource<PkMovingEntity> pkMovingEntityDs;
    @Inject
    private CollectionDatasource<PkEntity, Long> pksDs;
    @Named("fieldGroup.pkEntity")
    private LookupPickerField pkEntityField;
    @Named("fieldGroup.currentLocation")
    private TextField currentLocationField;

    @Override
    public void init(Map<String, Object> params) {
        pkEntityField.addValueChangeListener(e -> {
            PkEntity pkEntity = (PkEntity) e.getValue();
            currentLocationField.setValue(pkEntity.getLocation());
        });
    }

    @Override
    protected void postInit() {
        if (pkEntityField.getValue() != null) {
            PkEntity pkEntity = pkEntityField.getValue();
            currentLocationField.setValue(pkEntity.getLocation());
        }
    }

    @Override
    protected boolean preCommit() {
        PkMovingEntity item = getItem();
        List<HddEntity> hardDisks = item.getPkEntity().getHardDisks();
        boolean result = hardDisks.stream().allMatch(HddEntity::getIsFormatted);
        if (!result) {
            showNotification("Перед перемещение необходимо удалить всю информацию, находящуюся на жестких носителях ПК", NotificationType.WARNING);
            return false;
        }
        return true;
    }
}