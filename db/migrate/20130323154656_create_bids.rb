class CreateBids < ActiveRecord::Migration
  def change
    create_table :bids do |t|
      t.decimal :price
      t.integer :user_id

      t.timestamps
    end
  end
end
