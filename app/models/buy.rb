class Buy < ActiveRecord::Base
  attr_accessible :prediction_id, :price, :user_id
  belongs_to :prediction
  belongs_to :user
end
