package by.ItAlex.AlexTest.adapter;

import by.ItAlex.AlexTest.dto.Tool;
import by.ItAlex.AlexTest.model.ToolEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component

public class ToolAdapter implements Adapter<Tool,ToolEntity>{

    public ToolAdapter(){

    }
    private InfoAdapter infoAdapter;

    public void setInfoAdapter(InfoAdapter infoAdapter) {
        this.infoAdapter = infoAdapter;
    }
    @Override
    public Tool modelToDto(ToolEntity entity) {
        Tool tool = new Tool();
        if (entity!=null) {
            tool.setId(entity.getId());
            tool.setName(entity.getToolName());
            tool.setInfos(entity.getInformationEntities()
                    .stream()
                    .map(infoAdapter::modelToDto)
                    .collect(Collectors.toList())
            );
        }
        return tool;
    }

    @Override
    public ToolEntity dtoToModel(Tool dto) {
        return null;
    }
}
