package top.codeease.stockwatch.component;

import javax.swing.*;

/**
 * @author by: 神秘的鱼仔
 * @ClassName: StockSettingsComponent
 * @Description: 设置组件类
 * @Date: 2024/7/5 下午4:03
 */
public class StockSettingsComponent {
    private JPanel panel;
    private JTextField stockSymbolTextField;

    public StockSettingsComponent() {
        panel = new JPanel();
        stockSymbolTextField = new JTextField(40);
        panel.add(new JLabel("请输入股票编号(用英文逗号分割):"));
        panel.add(stockSymbolTextField);
    }

    public JPanel getPanel() {
        return panel;
    }

    public String getStockSymbol() {
        return stockSymbolTextField.getText();
    }

    public void setStockSymbol(String stockSymbol) {
        stockSymbolTextField.setText(stockSymbol);
    }
}
