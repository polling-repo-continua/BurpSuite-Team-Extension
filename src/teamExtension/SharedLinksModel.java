package teamExtension;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SharedLinksModel extends AbstractTableModel {

    private ArrayList<HttpRequestResponse> httpRequestResponses;
    private SharedValues sharedValues;

    SharedLinksModel(SharedValues sharedValues) {
        this.sharedValues = sharedValues;
        httpRequestResponses = new ArrayList<>();
    }

    void addBurpMessage(HttpRequestResponse burpMessage) {
        httpRequestResponses.add(burpMessage);
        fireTableDataChanged();
    }

    void removeBurpMessage(int rowIndex) {
        httpRequestResponses.remove(rowIndex);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return httpRequestResponses.size();
    }

    public String getColumnName(int col) {
        return "URL";
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        return sharedValues.getCallbacks().getHelpers().analyzeRequest(
                httpRequestResponses.get(rowIndex)).getUrl().toString();
    }

    HttpRequestResponse getBurpMessageAtIndex(int rowIndex) {
        return httpRequestResponses.get(rowIndex);
    }
}
