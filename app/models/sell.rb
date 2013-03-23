class Sell < ActiveRecord::Base
  attr_accessible :number_of_shares, :user_id, :prediction_id
  belongs_to :prediction
  belongs_to :user
end
