package models;

import java.util.List;

public interface ISequenceDetector {
    List<Sequence> search(GameField field);
}
