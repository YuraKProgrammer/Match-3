package tests;

public class SequenceDetector_Test {
    /*
    @Test
    public void search_Test1(){
        GameField field = new GameField(4,4);
        for (var x=0; x<field.getWidth(); x++){
            for (var y=0; y<field.getHeight(); y++){
                field.setChip(x,y,y*field.getWidth()+x);
            }
        }
        SequenceDetector detector = new SequenceDetector();
        var list = detector.search(field);
        Assertions.assertTrue(list.isEmpty());
    }
    @Test
    public void search_Test2(){
        GameField field = new GameField(3,3);
        field.setChip(0,0,1);
        field.setChip(1,0,1);
        field.setChip(2,0,1);
        field.setChip(0,1, 2);
        field.setChip(1,1, 3);
        field.setChip(2,1, 4);
        field.setChip(0,2, 5);
        field.setChip(1,2, 6);
        field.setChip(2,2, 7);
        SequenceDetector detector = new SequenceDetector();
        var result = detector.search(field);
        Sequence sequence = new Sequence(0,0,3,1);
        Assertions.assertEquals(1, result.size());
        Assertions.assertTrue(areEquals(result.get(0),sequence));
    }
    @Test
    public void search_Test3(){
        GameField field = new GameField(4,4);
        field.setChip(0,0,2);
        field.setChip(1,0,3);
        field.setChip(2,0,4);
        field.setChip(3,0,5);
        field.setChip(0,1, 6);
        field.setChip(1,1, 1);
        field.setChip(2,1, 7);
        field.setChip(3,1, 8);
        field.setChip(0,2, 9);
        field.setChip(1,2, 1);
        field.setChip(2,2, 10);
        field.setChip(3,2, 11);
        field.setChip(0,3, 12);
        field.setChip(1,3, 1);
        field.setChip(2,3, 13);
        field.setChip(3,3, 14);
        SequenceDetector detector = new SequenceDetector();
        var result = detector.search(field);
        Sequence sequence = new Sequence(1,1,1,3);
        Assertions.assertEquals(1, result.size());
        Assertions.assertTrue(areEquals(result.get(0),sequence));
    }
    @Test
    public void search_Test4(){
        GameField field = new GameField(10,10);
        for (var x=0; x<field.getWidth(); x++){
            for (var y=0; y<field.getHeight(); y++){
                field.setChip(x,y,y*field.getWidth()+x);
            }
        }
        SequenceDetector detector = new SequenceDetector();
        var list = detector.search(field);
        Assertions.assertEquals(true,list.isEmpty());
    }
    @Test
    public void search_Test5(){
        GameField field = new GameField(3,3);
        field.setChip(0,0,1);
        field.setChip(1,0,1);
        field.setChip(2,0,1);
        field.setChip(0,1,1);
        field.setChip(1,1, 1);
        field.setChip(2,1, 1);
        field.setChip(0,2, 1);
        field.setChip(1,2, 1);
        field.setChip(2,2, 1);
        SequenceDetector detector = new SequenceDetector();
        var result = detector.search(field);
        Assertions.assertEquals(6, result.size());
    }
    @Test
    public void search_Test6(){
        GameField field = new GameField(10,10);
        for (var x=0; x<field.getWidth(); x++){
            for (var y=0; y<field.getHeight(); y++){
                field.setChip(x,y,0);
            }
        }
        field.setChip(0,0,1);
        field.setChip(1,0,1);
        field.setChip(2,0,1);
        field.setChip(3,3,2);
        field.setChip(3,4,2);
        field.setChip(3,5,2);
        SequenceDetector detector = new SequenceDetector();
        var result = detector.search(field);
        Assertions.assertEquals(2, result.size());
    }
    @Test
    public void search_Test7(){
        GameField field = new GameField(10,10);
        for (var x=0; x<field.getWidth(); x++){
            for (var y=0; y<field.getHeight(); y++){
                field.setChip(x,y,0);
            }
        }
        field.setChip(7,0,1);
        field.setChip(8,0,1);
        field.setChip(9,0,1);
        field.setChip(0,7,2);
        field.setChip(0,8,2);
        field.setChip(0,9,2);
        SequenceDetector detector = new SequenceDetector();
        var result = detector.search(field);
        Assertions.assertEquals(2, result.size());
    }
    @Test
    public void search_Test8(){
        GameField field = new GameField(10,10);
        for (var x=0; x<field.getWidth(); x++){
            for (var y=0; y<field.getHeight(); y++){
                field.setChip(x,y,0);
            }
        }
        field.setChip(6,0,1);
        field.setChip(7,0,1);
        field.setChip(8,0,1);
        field.setChip(0,6,2);
        field.setChip(0,7,2);
        field.setChip(0,8,2);
        SequenceDetector detector = new SequenceDetector();
        var result = detector.search(field);
        Assertions.assertEquals(2, result.size());
    }

    private static boolean areEquals(Sequence s1, Sequence s2){
        if (s1.getX()==s2.getX() && s1.getY()==s2.getY() && s1.getWidth()==s2.getWidth() && s1.getHeight()==s2.getHeight()){
            return true;
        }
        return false;
    }
     */
}
