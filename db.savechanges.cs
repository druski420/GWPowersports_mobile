if (SQLFunctions.DbSaveChanges(db))
                            Logger.LogInfo($@"Part {tbManufPartNum} has been added");
                        else
                            Logger.LogError($@"Part {tbManufPartNum} could not be added");

if (SQLFunctions.DbSaveChanges(db))
                        Logger.LogWarning($@"Part {tbManufPartNum} existed, record has been updated");
                    else
                        Logger.LogError($@"Part {tbManufPartNum} could not be updated");

if (SQLFunctions.DbSaveChanges(db))
                        Logger.LogWarning($@"Part {partNum} has been deleted.");
                    else
                        Logger.LogError($@"Part {partNum} could not be deleted.");                                                    