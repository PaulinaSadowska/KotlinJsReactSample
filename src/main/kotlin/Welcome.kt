import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.attrs
import react.dom.button
import react.dom.div
import react.dom.img
import styled.css
import styled.styledDiv
import styled.styledInput

external interface WelcomeProps : RProps {
    var name: String
}

data class WelcomeState(val name: String) : RState

@JsExport
class Welcome(props: WelcomeProps) : RComponent<WelcomeProps, WelcomeState>(props) {

    init {
        state = WelcomeState(props.name)
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                +WelcomeStyles.textContainer
            }
            +"Hello, ${state.name}, your name reversed ${state.name.reversed()}"
        }
        styledInput {
            css {
                +WelcomeStyles.textInput
            }
            attrs {
                type = InputType.text
                value = state.name
                onChangeFunction = { event ->
                    setState(
                        WelcomeState(name = (event.target as HTMLInputElement).value)
                    )
                }
            }
        }
        div{
            button{
                attrs.onClickFunction = {
                    setState(
                        WelcomeState(name = "changed name")
                    )
                }
                +"Change name"
            }
        }
        div {
            img(src = "https://placekitten.com/408/287"){}
        }
    }
}
