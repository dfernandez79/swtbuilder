package swtbuilder;

public interface CompositeBuilder {

    <T extends LayoutAwareControlFactory> T add(T controlFactory);

    default <T extends LayoutAwareControlFactory> T add(String id, T controlFactory) {
        if (id != null) {
            add(new IdentifiableControlFactory(id, controlFactory));
        } else {
            add(controlFactory);
        }
        return controlFactory;
    }

    default LabelDescription label() {
        return label(null);
    }

    default LabelDescription label(String id) {
        return add(id, new LabelDescription());
    }

}
