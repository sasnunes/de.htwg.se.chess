package de.htwg.chess;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import de.htwg.chess.model.IChesspiece;
import de.htwg.chess.model.MoveCheckerVisitor;

public class MyActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private MoveCheckerVisitor movechecker;

    public MyActor(MoveCheckerVisitor movechecker) {
        this.movechecker = movechecker;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(IChesspiece.class, piece -> {
                    //System.out.printf("%s received chesspiece: %s%n", this.getContext() ,piece.toString());
                    piece.checkPossibleMoves(movechecker);
                })
                .matchAny(o -> log.error("received unknown message"))
                .build();
    }
}
