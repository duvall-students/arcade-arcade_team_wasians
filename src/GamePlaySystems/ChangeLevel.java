public ChangeLevel {
	
  private void levelTransition(Level level, Player player) {
		if (level.areAllLevelsPassed(levelNum)) {
			level.winningMessage();
			System.exit(0);
		}
		levelNum += levelUpNum;
		file.writeToFile(player.getScore());
		player.setReadytoPlay(false);
		myStage.close();
		start(new Stage());
	}
  
  }
