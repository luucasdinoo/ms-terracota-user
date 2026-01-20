package br.com.terracota.application;

public abstract class UnitUseCase<I> {

    public abstract void execute(I input);
}
