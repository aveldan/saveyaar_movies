import React from 'react';
import {Text, StyleSheet} from 'react-native';
import { Scale, COLORS } from '../constants/constIndex'

export default  function TextComponent (props){
  let _text = props?.text || '';
  let _style = props?.style || null;
  let _color = props?.color;
  let _numberOfLine = props?.numberOfLine || 1;
  let _fontSize = props?.size || 14;
  let _font =
    (props?.regular && 'regular') ||
    (props?.medium && 'medium') ||
    (props?.bold && 'bold') ||
    'regular';

  return (
    <Text
      numberOfLines={_numberOfLine}
      style={[
        styles.text,
        _color && {color: _color},
        _fontSize && {fontSize: Scale(_fontSize)},
        _font && {fontFamily: _font},
        _style && {..._style},

      ]}>
      {_text}
    </Text>
  );
};

const styles = StyleSheet.create({
  text: {
    fontSize: Scale(20),
    color: COLORS.black,
  },
});