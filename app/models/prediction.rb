class Prediction < ActiveRecord::Base
  attr_accessible :end_date, :name, :prediction, :source, :verified_by
  has_many :sells
  has_many :buys
  has_many :transactions
  belongs_to :user
end
