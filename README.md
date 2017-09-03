# CountDownLatchSample
JavaのCountDownLatchのサンプルプログラム

# 仕様
複数スレッドに計算をさせ、計算結果を最後に合計する。
この設計では、各スレッドの計算が全て終わってから合計処理を行う必要があるので、合計処理はスレッドの計算完了を待機しなければならない。
この待機機能にCountDownLatchを使う。

# 処理
1. メインスレッドはワーカースレッドを複数起動し、計算をさせる。
2. メインスレッドはCountDownLatch#awaitにより、ワーカースレッドの計算終了を待機する。
3. 計算が終わったワーカースレッドはCountDownLatch#countDownを呼び出し、CountDownLatchのカウントを減らす。
4. CountDownLatchのカウントが0になると、メインスレッドの処理が再開する。
5. メインスレッドは、ワーカースレッドの計算結果を全ての合計し、合計値をコンソール出力する。
