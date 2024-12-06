import { StyleSheet } from 'react-native';
import Scale from '@/constants/Scale';
import { height } from '@/constants/Dimensions';

export const styles = StyleSheet.create({
  conatinerImage: {
    width: '100%',
    height: height,
    position: 'absolute',
  },
  scrollContainer: {
    flexGrow: 1,
    padding: 20,
  },
  profileImg: {
    height: Scale(72),
    width: Scale(72),
    borderRadius: Scale(36),
    marginRight: 20,
  },
  headerViewStyle: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  info: {
    flex: 1, 
    flexDirection: 'column',
    alignItems: 'flex-start',
    justifyContent: 'center',
  },
  flagImage: {
    width: Scale(32),
    height: Scale(32),
  },
  textStyle: {
    // marginTop: Scale(5),
  },
  rowViewStyle: {
    flex: 1,
    width: Scale(354),
    height: Scale(260),
    paddingHorizontal: 10,
    paddingVertical: 20,
    alignItems: 'flex-start',
    justifyContent: 'flex-start',
  },
  optionViewStyle: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    width: 354,
    height: 52,
  },
  iconViewStyle: {
    flex: 1,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'flex-start',
    paddingRight: 10,
  },
  arrowViewStyle: {
    flex: 0.1,
    alignItems: 'center',
    justifyContent: 'center',
    padding: Scale(5),
  },
  iconStyle: {
    width: Scale(25),
    height: Scale(25),
    alignSelf: 'center',
  },
  headingStyle: {
    textAlign: 'left',
  },
});
