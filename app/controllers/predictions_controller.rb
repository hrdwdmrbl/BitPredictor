class PredictionsController < InheritedResources::Base
  def create
    params[:prediction][:user_id] = current_user.id
    create!
  end

  def yes
    @prediction = Prediction.find(params[:id])
    @prediction.outcome = 'yes'

    #cancel incomplete transaction
    @prediction.buys.incomplete.destroy_all
    @prediction.sells.incomplete.destroy_all

    @prediction.transactions.each{|transaction| transaction.execute('yes') }
    @prediction.save
    redirect_to prediction_path(@prediction)
  end
  def no
    @prediction = Prediction.find(params[:id])
    @prediction.outcome = 'no'

    #cancel incomplete transaction
    @prediction.buys.incomplete.destroy_all
    @prediction.sells.incomplete.destroy_all

    @prediction.transactions.each{|transaction| transaction.execute('no') }
    @prediction.save
    redirect_to prediction_path(@prediction)
  end
end
