package by.ItAlex.AlexTest.adapter;

public interface Adapter <D,E>{

    D modelToDto(E entity);

    E dtoToModel(D dto);
}
