package swtbuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class TreeDescription extends AbstractControlDescription<TreeDescription, Tree> {
    private final TreeItemDescription root = new TreeItemDescription();

    public TreeDescription() {
        super(Tree::new);
    }

    @Override
    protected void setUpControl(Tree control, ControlRefs refs) {
        for (TreeItemDescription item : root.childs) {
            item.createItem(control);
        }
    }

    public TreeDescription item(String text) {
        return item(text, t -> {});
    }

    public TreeDescription item(String text, Consumer<TreeItemDescription> fn) {
        root.item(text, fn);
        return this;
    }

    public class TreeItemDescription {
        private String text;
        private final List<TreeItemDescription> childs = new ArrayList<TreeItemDescription>();

        public TreeItemDescription text(String text) {
            this.text = text;
            return this;
        }

        private void createItem(Tree parent) {
            setUpItem(new TreeItem(parent, SWT.NONE));
        }

        private void createItem(TreeItem parent) {
            setUpItem(new TreeItem(parent, SWT.NONE));
        }

        private void setUpItem(TreeItem item) {
            item.setText(text);
            for (TreeItemDescription itemDescription : childs) {
                itemDescription.createItem(item);
            }
        }

        public TreeItemDescription item(String text) {
            return item(text, t -> {});
        }

        public TreeItemDescription item(String text, Consumer<TreeItemDescription> fn) {
            final TreeItemDescription newItem = new TreeItemDescription().text(text);
            childs.add(newItem);
            fn.accept(newItem);
            return this;
        }
    }
}
