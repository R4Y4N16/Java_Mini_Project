package OOP.ec22431.A8;

interface Visitable {
    
    Direction visit( // Returns direction the visitor leaves towards.
        Visitor visitor,
        Direction directionVistorArrivesFrom);
}