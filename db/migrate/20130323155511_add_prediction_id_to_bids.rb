class AddPredictionIdToBids < ActiveRecord::Migration
  def change
    add_column :bids, :prediction_id, :integer
  end
end
