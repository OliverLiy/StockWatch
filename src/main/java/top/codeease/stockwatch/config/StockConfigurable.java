package top.codeease.stockwatch.config;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import top.codeease.stockwatch.component.StockSettingsComponent;
import top.codeease.stockwatch.storage.StockSettingsState;

import javax.swing.*;

/**
 * @author by: 神秘的鱼仔
 * @ClassName: StockConfigurable
 * @Description: 股票配置的最终实现
 * @Date: 2024/7/9 上午10:09
 */
public class StockConfigurable implements Configurable {
    private StockSettingsComponent settingsComponent;

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) String getDisplayName() {
        return "Stock Plugin";
    }

    @Override
    public @Nullable JComponent createComponent() {
        settingsComponent = new StockSettingsComponent();
        return settingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        StockSettingsState settings = StockSettingsState.getInstance();
        return !settingsComponent.getStockSymbol().equals(settings.stockSymbol);
    }

    @Override
    public void apply() {
        StockSettingsState settings = StockSettingsState.getInstance();
        settings.stockSymbol = settingsComponent.getStockSymbol();
    }

    @Override
    public void reset() {
        StockSettingsState settings = StockSettingsState.getInstance();
        settingsComponent.setStockSymbol(settings.stockSymbol);
    }
}
