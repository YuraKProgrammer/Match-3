package models.detectors;

import models.GameField;
import models.Sequence;

import java.util.List;

public interface ISequenceDetector {
    List<Sequence> search(GameField field);
}
