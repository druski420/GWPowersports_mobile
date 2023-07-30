try
            {
                UseWaitCursor = true;                


            }
            catch (Exception ex)
            {
                Logger.LogError(@"Exception:");
                Logger.LogError($@"{ex.Message}");
            }
            finally
            {
                UseWaitCursor = false;
            }