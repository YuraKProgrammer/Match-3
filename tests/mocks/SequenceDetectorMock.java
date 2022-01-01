package tests.mocks;

import models.ChipImageType;
import models.GameField;
import models.ISequenceDetector;
import models.Sequence;

import java.util.ArrayList;
import java.util.List;

public class SequenceDetectorMock implements ISequenceDetector {
    @Override
    public List<Sequence> search(GameField field) {
        ArrayList<Sequence> sequences = new ArrayList<>();
        sequences.add(new Sequence(0,0,0,0, ChipImageType.T1));
        return sequences;
    }
}
