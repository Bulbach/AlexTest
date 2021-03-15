package by.ItAlex.AlexTest.adapter;

import by.ItAlex.AlexTest.dto.Info;
import by.ItAlex.AlexTest.model.InformationEntity;
import org.springframework.stereotype.Component;

@Component

public class InfoAdapter implements Adapter<Info, InformationEntity> {

    private ToolAdapter toolAdapter;

    public InfoAdapter() {

    }

    public void setToolAdapter(ToolAdapter toolAdapter) {
        this.toolAdapter = toolAdapter;
    }

    public Info modelToDto(InformationEntity informationEntity) {
        Info info = new Info();
        if (informationEntity != null) {

            info.setId(informationEntity.getId());
            info.setDate(informationEntity.getDate());
            info.setPrice(informationEntity.getPrice());
            info.setTool(toolAdapter.modelToDto(informationEntity.getToolEntity()));
        }

        return info;
    }

    public InformationEntity dtoToModel(Info info) {

        InformationEntity entity = new InformationEntity();
        if (info != null) {
            entity.setId(info.getId());
            entity.setDate(info.getDate());
            entity.setPrice(info.getPrice());
            entity.setToolEntity(toolAdapter.dtoToModel(info.getTool()));
        }
        return entity;
    }
}
