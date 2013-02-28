package sh.calaba.instrumentationbackend.actions.dojo;

import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.TestHelpers;
import sh.calaba.instrumentationbackend.actions.Action;
import android.view.View;
import android.widget.LinearLayout;

public class VerifyLastCoinColor implements Action {
	private static final int NUMBER_OF_ARGS = 3;
	
	/**
	 * Verify if the color of the pied coin on the viewId is same as the one you input or not.
	 * @param args[0]: viewId of the column to verify
	 * @param args[1]: index of the row to verify
	 * @param args[2]: color of the coin to verify
	 * @return Result: If the color of the coin is same as the one you input, Success result is returned. Otherwise, Failed result is returned.
	 */
	@Override
	public Result execute(String... args) {
		String viewId = args[0];
		String yPosition = args[1];
		String coinColor = args[2];
		
		if ( args.length < NUMBER_OF_ARGS ) {
			return Result.failedResult("Illegal argument");
		}
		
		final View view = TestHelpers.getViewById(viewId);
		if (view == null) {
			return Result.failedResult("Could not find view with id: '" +
					viewId + "'");
		}

		if (!(view instanceof LinearLayout)) {
			return Result.failedResult("View isn't linearLayout: '" + viewId + "'");
		}

		LinearLayout layout = (LinearLayout)view;
		int expectedNumberOfCoins = Integer.parseInt(yPosition) + 1; // convert from index of coin's position to number of coins
		int actualNumberOfCoins = layout.getChildCount();
		if (actualNumberOfCoins != expectedNumberOfCoins) {
			return Result.failedResult("The number of coins is invalid; expected: " + expectedNumberOfCoins + ", actual: "  + actualNumberOfCoins);
		}
		
		View coinView = getTopCoinView(layout);
		if (coinView == null) {
			return Result.failedResult("No coins");
		}

		if (!isExistingCoin(coinView, coinColor)) {
			return Result.failedResult("The coin is invalid: expected;" + coinColor + ", actual: " + coinView.getTag());
		}
		return Result.successResult();
	}

	@Override
	public String key() {
		return "verify_last_coin_color";
	}
	
	private Boolean isExistingCoin(View coinView, String coinColor) {
		return coinView.getTag().equals(coinColor);
	}
	
	private View getTopCoinView(LinearLayout layout) {
		// on LinearLayout, the latest added child view has index 0.
		return layout.getChildAt(0);
	}
}
