package top.codeease.stockwatch.response;

import lombok.Data;

/**
 * @author by: 神秘的鱼仔
 * @ClassName: GetStockPriceResponse
 * @Description: 股票结果返回
 * @Date: 2024/7/12 下午5:03
 */
@Data
public class GetStockPriceResponse {
    /**
     * 股票名称
     */
    private String stockName;
    /**
     * 股票编号
     */
    private String stockNo;
    /**
     * 当前价格
     */
    private String lastTrade;
    /**
     * 涨跌幅
     */
    private String change;
    /**
     * 昨收
     */
    private String prevClose;
    /**
     * 今开
     */
    private String open;
}
