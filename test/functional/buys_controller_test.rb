require 'test_helper'

class BuysControllerTest < ActionController::TestCase
  setup do
    @buy = buys(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:buys)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create buy" do
    assert_difference('Buy.count') do
      post :create, buy: { prediction_id: @buy.prediction_id, price: @buy.price, user_id: @buy.user_id }
    end

    assert_redirected_to buy_path(assigns(:buy))
  end

  test "should show buy" do
    get :show, id: @buy
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @buy
    assert_response :success
  end

  test "should update buy" do
    put :update, id: @buy, buy: { prediction_id: @buy.prediction_id, price: @buy.price, user_id: @buy.user_id }
    assert_redirected_to buy_path(assigns(:buy))
  end

  test "should destroy buy" do
    assert_difference('Buy.count', -1) do
      delete :destroy, id: @buy
    end

    assert_redirected_to buys_path
  end
end
