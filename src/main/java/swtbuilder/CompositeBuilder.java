package swtbuilder;

public interface CompositeBuilder {

    <T extends ControlDescription> T add(T description);

    default LabelDescription label() {
        return label(null);
    }

    default LabelDescription label(String id) {
        return add(new LabelDescription(id));
    }

}
