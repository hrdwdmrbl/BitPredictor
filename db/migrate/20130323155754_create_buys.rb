class CreateBuys < ActiveRecord::Migration
  def change
    create_table :buys do |t|
      t.decimal :price
      t.integer :user_id
      t.integer :prediction_id

      t.timestamps
    end
  end
end
