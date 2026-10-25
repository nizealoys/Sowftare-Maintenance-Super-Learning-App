package src.core.model;

interface Aggregate {
    Iterator<Feature> createIterator();
}
