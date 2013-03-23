class Transaction < ActiveRecord::Base
  attr_accessible :buy_id, :prediction_id, :price, :number_of_shares, :sell_id
  belongs_to :prediction
  belongs_to :buy
  belongs_to :sell
end
