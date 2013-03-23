class RenameTransactionBuyerAndSellerToBuyAndSell < ActiveRecord::Migration
  def up
    rename_column :transactions, :buyer_id, :buy_id
    rename_column :transactions, :seller_id, :sell_id
  end

  def down
    rename_column :transactions, :buy_id, :buyer_id
    rename_column :transactions, :sell_id, :seller_id
  end
end
