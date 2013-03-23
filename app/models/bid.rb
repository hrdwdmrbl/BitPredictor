class Bid < ActiveRecord::Base
  attr_accessible :price, :user_id, :prediction_id
  belongs_to :prediction
  belongs_to :user
end
