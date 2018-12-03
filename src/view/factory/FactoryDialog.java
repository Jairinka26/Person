package view.factory;

public class FactoryDialog{
    private static FactoryDialog instance = null;

    private FactoryDialog() {}

    public static synchronized FactoryDialog getInstance() {
        if (instance == null){
            instance = new FactoryDialog();
        }
        return instance;
    }

    public <T>IDialogFactory factoryMethod(T dialog, String cmd){
        switch (cmd) {
            case "create" :   if (dialog instanceof DialogCreate.DialogCreateCallBack) {
                return new DialogCreate<>((DialogCreate.DialogCreateCallBack) dialog);
            }
            case "update" :   if (dialog instanceof DialogUpdate.DialogUpdateCallBack) {
                return new DialogUpdate<>((DialogUpdate.DialogUpdateCallBack) dialog);
            }
            case "delete" :   if (dialog instanceof DialogDelete.DialogDeleteCallBack) {
                return new DialogDelete<>((DialogDelete.DialogDeleteCallBack) dialog);
            }
            default: return null;
        }
    }
}
