class CreateTransactions < ActiveRecord::Migration
  def change
    create_table :transactions do |t|
      t.integer :buyer_id
      t.integer :seller_id
      t.integer :price
      t.integer :quantity
      t.integer :prediction_id

      t.timestamps
    end
  end
end
