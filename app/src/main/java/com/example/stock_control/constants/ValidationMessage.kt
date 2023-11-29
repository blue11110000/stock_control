package com.example.stock_control.constants

object ValidationMessage {
    const val EMPTY =""

    val SUPPLIER_EMPTY_MESSAGE = listOf("取引先名を入力してください",
                                        "電話番号を入力してください",
                                        "メールを入力してください")

    val PRODUCT_EMPTY_MESSAGE = listOf("品番を入力してください",
                                        "商品名を入力してください",
                                        "取引先IDを入力してください",
                                        "取引先名を入力してください",
                                        "仕入価格を入力してください",
                                        "販売価格を入力してください",
                                        "最低在庫を入力してください")

    val STOCK_EMPTY_MESSAGE = listOf("商品IDを入力してください",
                                    "品番を入力してください",
                                    "商品名を入力してください",
                                    "日付を入力してください",
                                    "個数を入力してください")

}