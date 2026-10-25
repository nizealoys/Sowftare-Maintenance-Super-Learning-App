//Generé par IA

package src.core.model;

import java.util.ArrayDeque;
import java.util.Deque;

public class FeatureIterator implements Iterator<Feature> {
    private final Deque<Feature> stack = new ArrayDeque<>();

    FeatureIterator(Feature root) {
        if (root != null) {
            stack.push(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Feature next() {
        Feature current = stack.pop();
        // on empile à l'envers pour garder l'ordre d'insertion
        for (int i = current.getChildren().size() - 1; i >= 0; i--) {
            stack.push(current.getChildren().get(i));
        }
        return current;
    }
}