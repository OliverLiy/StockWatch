package top.codeease.stockwatch.window;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.table.JBTable;
import org.jetbrains.annotations.NotNull;
import top.codeease.stockwatch.handler.StockPriceHandler;
import top.codeease.stockwatch.response.GetStockPriceResponse;
import top.codeease.stockwatch.storage.StockSettingsState;
import top.codeease.stockwatch.util.DateUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * @author by: 神秘的鱼仔
 * @ClassName: StockToolWindow
 * @Description: 股票展示页面
 * @Date: 2024/7/11 下午11:04
 */
public class StockToolWindow implements ToolWindowFactory {
    private JPanel mainPanel;
    private JButton refreshButton;
    private JBTable stockTable;
    private DefaultTableModel tableModel;
    private JLabel lastUpdatedLabel;
    private final String[] columnNames = {"股票名称", "股票编号","当前价格","涨跌%","昨收","今开"};

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        mainPanel = new JPanel(new BorderLayout());

        // 创建按钮
        JPanel buttonPanel = new JPanel();
        refreshButton = new JButton("Refresh");
        lastUpdatedLabel = new JLabel("Last Updated: " + DateUtil.getNowTime());
        buttonPanel.add(refreshButton);
        buttonPanel.add(lastUpdatedLabel);

        // 创建表格
        Object[][] data = getDataArray();
        tableModel = new DefaultTableModel(data, columnNames);
        stockTable = new JBTable(tableModel);

        // 添加组件到主面板
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(stockTable), BorderLayout.CENTER);

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(mainPanel, "", false);
        toolWindow.getContentManager().addContent(content);

        // 添加按钮操作逻辑
        refreshButton.addActionListener(e -> refreshFetchingStockPrice());
    }

    /**
     * 刷新股票的价格数据
     */
    private void refreshFetchingStockPrice() {
        Object[][] data = getDataArray();
        tableModel = new DefaultTableModel(data, columnNames);
        stockTable.setModel(tableModel);
        lastUpdatedLabel.setText("Last Updated: " + DateUtil.getNowTime());
    }

    /**
     * 获取表格的数据列
     * @return
     */
    private Object[][] getDataArray() {
        String stockSymbol = StockSettingsState.getInstance().stockSymbol;
        StockPriceHandler handler = new StockPriceHandler();
        List<GetStockPriceResponse> getStockPriceResponseList = handler.fetchStockPrice(stockSymbol);
        Object[][] data = new Object[getStockPriceResponseList.size()][];
        for (int i = 0; i < getStockPriceResponseList.size(); i++) {
            GetStockPriceResponse stockPriceResponse = getStockPriceResponseList.get(i);
            Object[] rowData = {stockPriceResponse.getStockName(),stockPriceResponse.getStockNo()
                    ,stockPriceResponse.getLastTrade(),stockPriceResponse.getChange()
                    ,stockPriceResponse.getPrevClose(),stockPriceResponse.getOpen()};
            data[i] = rowData;
        }
        return data;
    }

}
