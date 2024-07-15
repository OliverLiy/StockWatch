package top.codeease.stockwatch.storage;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author by: 神秘的鱼仔
 * @ClassName: StockSettingsState
 * @Description: 股票配置的持久化类
 * @Date: 2024/7/5 下午4:17
 */
@State(
        name = "top.codeease.stockwatch.storage.StockSettingsState",
        storages = @Storage("StockPluginSettings.xml")
)
public class StockSettingsState implements PersistentStateComponent<StockSettingsState> {
    /**
     * 默认股票符号，取上证指数
     */
    public String stockSymbol = "sh000001";

    public static StockSettingsState getInstance() {
        return ServiceManager.getService(StockSettingsState.class);
    }

    @Nullable
    @Override
    public StockSettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull StockSettingsState state) {
        this.stockSymbol = state.stockSymbol;
    }
}
