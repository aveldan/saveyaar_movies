import React from 'react';
import {Text, StyleSheet} from 'react-native';
import { COLORS } from '../constants/constIndex'
import Scale from '@/constants/Scale';

interface TextComp{
  text?: string,
  style?: any,
  color?: any,
  numberOfLine?: number,
  size?: number,
  bold?:Boolean,
  medium?: Boolean,
  regular?: Boolean
};

export default  function TextComponent ({text = '', 
                                        style = null, 
                                        color = null,
                                        numberOfLine = 1,
                                        size = 14,
                                        bold = false,
                                        medium = false,
                                        regular = false} : TextComp){
  let _text = text || '';
  let _style = style || null;
  let _color = color;
  let _numberOfLine = numberOfLine || 1;
  let _fontSize = size || 14;
  let _font =
    (regular && 'regular') ||
    (medium && 'medium') ||
    (bold && 'bold') ||
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
    color: "#fff",
  },
});