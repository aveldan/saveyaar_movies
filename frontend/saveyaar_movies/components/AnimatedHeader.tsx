import React from "react";
import Animated, { interpolate, useAnimatedStyle } from "react-native-reanimated";
import { StyleSheet } from "react-native";
import { useSafeAreaInsets } from "react-native-safe-area-context";

interface AnimatedHeaderProps {
    scrollDirection: Animated.SharedValue<number>;
    children: React.ReactNode
}


export default function AnimatedHeader({scrollDirection, children}: AnimatedHeaderProps) {

    const insets = useSafeAreaInsets();    
    const headerAnimatedStyle = useAnimatedStyle(() => {
        return {
            opacity: interpolate(
                scrollDirection.value,
                [0, 0.5, 1],
                [1, 0.8, 0],
                'clamp'
            ),
            transform: [
                {
                    translateY: interpolate(
                        scrollDirection.value,
                        [0, 1],
                        [0, -40],
                        'clamp'
                    )
                }
            ],
            overflow: 'visible',
            height: interpolate(
                scrollDirection.value,
                [0, 1],
                [47, 0],
                'clamp'
            ),
        };
    });

    return (
        <Animated.View style={[styles.header, {paddingTop: insets.top}, headerAnimatedStyle]}>
            {children}
        </Animated.View>
    );
};

const styles = StyleSheet.create({
    header: {
        position: 'absolute',
        top: 0,
        left: 0,
        right: 0,
    },
});