package view;

import presenter.TicTacPresenter;

public interface TicTacView {

    void start(String field);

    void update(String field);

    void attachPresenter(TicTacPresenter presenter);

    void end(String winner);
}

