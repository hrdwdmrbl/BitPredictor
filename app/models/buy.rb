class Buy < ActiveRecord::Base
  attr_accessible :prediction_id, :number_of_shares, :user_id
  belongs_to :prediction
  belongs_to :user
end
