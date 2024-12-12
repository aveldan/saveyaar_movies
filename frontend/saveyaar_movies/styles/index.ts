import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
    scrollView: {
        flex: 1,
        paddingHorizontal: 10,
        backgroundColor: 'transparent',
        flexDirection: 'column',
    },
    headerTitle: {
        color: '#fff',
        fontSize: 24,
        fontWeight: 'bold',
    },
    headerButtons: {
        flexDirection: 'row',
        gap: 4,
    },
    blurContainer: {
        width: '100%',
    }
});