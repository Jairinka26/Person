package control;

import java.io.IOException;

public interface IControler {
    void create();

    void read();

    void update();

    void delete();

    void search();

    void export() throws IOException;

    void _import() throws IOException;

}
