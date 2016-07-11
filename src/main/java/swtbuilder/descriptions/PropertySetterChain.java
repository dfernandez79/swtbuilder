package swtbuilder.descriptions;

public interface PropertySetterChain<D extends PropertySetterChain> {

    D set(String propertyName, Object value);

}
