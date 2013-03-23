class BuysController < InheritedResources::Base
    belongs_to :prediction, :optional => true
    def create
        params[:buy][:user_id] = current_user.id
        create!
    end
    def accept
        @prediction = Prediction.find(params[:buy][:prediction_id])
        @buy = Buy.find(params[:id])
        @sell = Sell.create(
            price: @buy.price,
            number_of_shares: @buy.number_of_shares,
            user_id: current_user.id,
            prediction_id: @prediction.id
        )
        @transaction = Transaction.create(
            buy_id: @buy.id,
            sell_id: @sell.id,
            number_of_shares: @buy.number_of_shares,
            prediction_id: @prediction.id
        )
        @buy.transaction_id = @transaction.id
        @buy.save
        @sell.transaction_id = @transaction.id
        @sell.save
        flash[:notice] = "Transaction Executed saved!"
        redirect_to prediction_path(@prediction.id)
    end
end
